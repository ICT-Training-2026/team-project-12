package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.MyPage;
import com.example.demo.entity.Regist;
import com.example.demo.form.AttendForm;
import com.example.demo.form.EmployeeNumForm;
import com.example.demo.form.MyForm;
import com.example.demo.service.HolidayService;
import com.example.demo.service.NameService;
import com.example.demo.service.RegistExistService;
import com.example.demo.service.SubstitudeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegistController {
	
	private final NameService nservice;
	private final HolidayService hservice;
	private final SubstitudeService sservice;
	private final RegistExistService reservice;
	
	
	// マイページへ戻る
	@PostMapping("return_mypage")
	public String returnMypage(@ModelAttribute MyForm myform, Model model,@ModelAttribute EmployeeNumForm empform) {
		
		MyPage tmp = new MyPage();
		
		
		tmp = nservice.MyReference(myform.getEmployeeNum());
		
		model.addAttribute("employeenum",myform.getEmployeeNum());
		model.addAttribute("name",tmp.getName());
		model.addAttribute("hours",tmp.getHours());
		model.addAttribute("employeeNum",myform.getEmployeeNum());
		
		
		
		return "my-page";
	}
	
	
	@PostMapping("regist_confirm")
	public String registConfirm(@ModelAttribute MyForm myform, @Validated@ModelAttribute AttendForm attendform, @ModelAttribute EmployeeNumForm empform, Model model, BindingResult result) {
		
		model.addAttribute("employeeNum",myform.getEmployeeNum());
		
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
		
		
		// 二重登録時にエラー表示
		
		Regist regist = new Regist();
		
		regist.setEmployeeNum(attendform.getEmployeeNum());
		regist.setYear(attendform.getYear());
		
		boolean attendance = reservice.attendanceBridge(regist);
		
		if (attendance) {

			model.addAttribute("message", "選択している日付は既に勤怠登録されています");

			return "regist-attend";

		}
		
		// 出勤・振出の退勤時刻が22:45を過ぎているときにエラー表示
		if (("出勤".equals(attendform.getAttendanceType()) || "振出".equals(attendform.getAttendanceType())) && (attendform.getFinishHour() == 22) && (attendform.getFinishMinute() > 45)) {
			
			model.addAttribute("message", "退勤時刻は22:45以降にできません");

			return "regist-attend";
		}
		
		
		// 休憩時間が4桁以上の時にエラー表示
		if (attendform.getRestTime() > 999) {
			
			model.addAttribute("message", "休憩時間は４桁以上にできません");

			return "regist-attend";
			
		}
		
		
		// 年休・振休が休日になるときにエラーを返す
		
		Boolean compose = hservice.holidayBridge(attendform.getYear());
		
		
		int countPaidHoliday = hservice.paidHolidayBridge(attendform.getEmployeeNum());
		
		
		
		// 会社休日の日に申請できない区分のエラー文表示
		if(("出勤".equals(attendform.getAttendanceType()) || "年休".equals(attendform.getAttendanceType()) || "振休".equals(attendform.getAttendanceType()) || "欠勤".equals(attendform.getAttendanceType())) && compose) {
			
			
			model.addAttribute("message","出勤・年休・振休・欠勤は会社休日の日に申請できません");
			
			return "regist-attend";
		}
		
		// 有給申請で有給残日数が足りないときのエラー表示
		if("年休".equals(attendform.getAttendanceType()) && countPaidHoliday < 1) {
			
			model.addAttribute("message","有給残日数が足りません");
			
			return "regist-attend";
		}
		
		
		
		// 休日、振出の取得日付が平日になるときにエラーを返す
		System.out.println("if年休振休：起動確認");
		
		
		if(("休日".equals(attendform.getAttendanceType()) || "振出".equals(attendform.getAttendanceType())) && !compose) {
			
			
			model.addAttribute("message","休日と振出は平日に申請できません");
			
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
