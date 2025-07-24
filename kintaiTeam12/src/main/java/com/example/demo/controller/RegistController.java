package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.MyPage;
import com.example.demo.form.AttendForm;
import com.example.demo.form.MyForm;
import com.example.demo.service.HolidayService;
import com.example.demo.service.NameService;
import com.example.demo.service.SubstitudeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegistController {
	
	private final NameService nservice;
	private final HolidayService hservice;
	private final SubstitudeService sservice;
	
	
	// マイページへ戻る
	@PostMapping("return_mypage")
	public String returnMypage(@ModelAttribute MyForm myform, Model model) {
		
		MyPage tmp = new MyPage();
		
		
		tmp = nservice.MyReference(myform.getEmployeeNum());
		
		model.addAttribute("employeenum",myform.getEmployeeNum());
		model.addAttribute("name",tmp.getName());
		model.addAttribute("hours",tmp.getHours());
		
		
		return "my-page";
	}
	
	
	@PostMapping("regist_confirm")
	public String registConfirm(@ModelAttribute MyForm myform, @Validated@ModelAttribute AttendForm attendform, Model model, BindingResult result) {
		
		// 入力エラー時に画面を戻す
		if (attendform.getYear() == null) {
			
			System.out.println("動作確認入力エラー");
			
			model.addAttribute("message","");
			
			return "regist-attend";
		}
		
		
		// 勤怠時刻の分：00で表示
		String formattedStartMinute = String.format("%02d", attendform.getStartMinute());
	    String formattedFinishMinute = String.format("%02d", attendform.getFinishMinute());
		
		model.addAttribute("employeenum", attendform.getEmployeeNum());
	    model.addAttribute("year", attendform.getYear());
	    model.addAttribute("attendanceType", attendform.getAttendanceType());
	    model.addAttribute("startHour", attendform.getStartHour());
	    model.addAttribute("startMinute", formattedStartMinute); // フォーマット済みの値を使用
	    model.addAttribute("finishHour", attendform.getFinishHour());
	    model.addAttribute("finishMinute", formattedFinishMinute); // フォーマット済みの値を使用
	    model.addAttribute("restTime", attendform.getRestTime());
		
		attendform.setEmployeeNum(myform.getEmployeeNum());
		
		
		// 年休・振休が休日になるときにエラーを返す
		
		Boolean compose = hservice.holidayBridge(attendform.getYear());
		
		
		int countPaidHoliday = hservice.paidHolidayBridge(attendform.getEmployeeNum());
		
		
		
		// 会社休日の日に申請できない区分のエラー文表示
		if(("年休".equals(attendform.getAttendanceType()) || "振休".equals(attendform.getAttendanceType()) || "欠勤".equals(attendform.getAttendanceType())) && compose) {
			
			
			model.addAttribute("message","年休・振休・欠勤は会社休日の日に取得できません");
			
			return "regist-attend";
		}
		
		// 有給申請で有給残日数が足りないときのエラー表示
		if("年休".equals(attendform.getAttendanceType()) && countPaidHoliday < 1) {
			
			model.addAttribute("message","有給残日数が足りません");
			
			return "regist-attend";
		}
		
		
		
		// 休日、振出の取得日付が平日になるときにエラーを返す
		System.out.println("if年休振休：起動確認");
		
		
		if("休日".equals(attendform.getAttendanceType()) || "振出".equals(attendform.getAttendanceType()) && !compose) {
			
			
			model.addAttribute("message","休日と振出は平日に取得できません");
			
			return "regist-attend";
		}
		
		
		
		// 振休残日数が0のとき、振出申請でエラー表示
		int countSubstitudeHoliday = sservice.substitudeBridge(attendform.getEmployeeNum());
		
		System.out.println("countSubstitudeHoliday：" + countSubstitudeHoliday);
		
		if ("振休".equals(attendform.getAttendanceType()) && countSubstitudeHoliday < 1) {
			
			model.addAttribute("message","振休残日数が足りません");
			
			return "regist-attend";
		}
		
		
		
		
		// 年休のとき、労働時間を固定
		if ("年休".equals(attendform.getAttendanceType())) {
			
			
			
			attendform.setStartHour(9);
			attendform.setStartMinute(0);
			attendform.setFinishHour(16);
			attendform.setFinishMinute(0);
			attendform.setRestTime(0);
			
			model.addAttribute("startHour",attendform.getStartHour());
			model.addAttribute("startMinute", formattedStartMinute);
			model.addAttribute("finishHour",attendform.getFinishHour());
			model.addAttribute("finishMinute", formattedFinishMinute);
			model.addAttribute("restTime", attendform.getRestTime());
			
		}

		// 休日、欠勤、振休のとき、労働時間を自動で0にする
		if ("休日".equals(attendform.getAttendanceType()) || "欠勤".equals(attendform.getAttendanceType()) || "振休".equals(attendform.getAttendanceType())) {

			attendform.setStartHour(0);
			attendform.setStartMinute(0);
			attendform.setFinishHour(0);
			attendform.setFinishMinute(0);
			attendform.setRestTime(0);

			model.addAttribute("startHour", attendform.getStartHour());
			model.addAttribute("startMinute", formattedStartMinute);
			model.addAttribute("finishHour", attendform.getFinishHour());
			model.addAttribute("finishMinute", formattedFinishMinute);
			model.addAttribute("restTime", attendform.getRestTime());

		}
		
		
		return "regist-confirm";
	}
	
	
}
