package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Summary;
import com.example.demo.form.AttendForm;
import com.example.demo.form.EmployeeNumForm;
import com.example.demo.form.MyForm;
import com.example.demo.form.SummaryForm;
import com.example.demo.service.EditDeleteService;
import com.example.demo.service.HolidayService;
import com.example.demo.service.SubstitudeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EditController {
	
	private final EditDeleteService eservice;
	private final HolidayService hservice;
	private final SubstitudeService sservice;
	
	// 編集削除のトップ画面へ戻る
	@PostMapping("return_edit_delete")
	public String returnEditDeletePage(@ModelAttribute MyForm myform, @ModelAttribute AttendForm registform, Model model, @ModelAttribute EmployeeNumForm empform) {

		model.addAttribute("employeenum", myform.getEmployeeNum());
		model.addAttribute("employeeNum",myform.getEmployeeNum());

		List<Summary> summaries = eservice.monthAttends(myform.getEmployeeNum());

		if (summaries.size() > 0) {
			model.addAttribute("resultList", summaries);
		}
		
		return "edit-delete";
	}
	
	@PostMapping("edit_confirm_page")
	public String editConfirmPage(@ModelAttribute MyForm myform, @ModelAttribute SummaryForm summaryform, Model model,
			@ModelAttribute EmployeeNumForm empform) {

		model.addAttribute("employeeNum", myform.getEmployeeNum());

		// 勤怠時刻の分：00で表示
		String formattedStartMinute = String.format("%02d", summaryform.getStartMinute());
		String formattedFinishMinute = String.format("%02d", summaryform.getFinishMinute());

		model.addAttribute("date", summaryform.getDate());
		model.addAttribute("employeenum", summaryform.getEmployeeNum());
		//		model.addAttribute("year", attendform.getYear());
		model.addAttribute("attendanceType", summaryform.getAttendanceType());
		model.addAttribute("startHour", summaryform.getStartHour());
		model.addAttribute("startMinute", formattedStartMinute); // フォーマット済みの値を使用
		model.addAttribute("finishHour", summaryform.getFinishHour());
		model.addAttribute("finishMinute", formattedFinishMinute); // フォーマット済みの値を使用
		model.addAttribute("restTime", summaryform.getRestTime());

		summaryform.setEmployeeNum(myform.getEmployeeNum());

		// 二重登録時にエラー表示

		//		Regist regist = new Regist();
		//
		//		regist.setEmployeeNum(summaryform.getEmployeeNum());
		//		regist.setYear(summaryform.getDate());
		//
		//		boolean attendance = reservice.attendanceBridge(regist);
		//
		//		if (attendance) {
		//
		//			model.addAttribute("message", "選択している日付は既に勤怠登録されています");
		//
		//			return "edit";
		//
		//		}

		// 年休のとき、労働時間を固定
		if ("年休".equals(summaryform.getAttendanceType())) {

			summaryform.setStartHour(9);
			summaryform.setStartMinute(0);
			summaryform.setFinishHour(17);
			summaryform.setFinishMinute(0);
			summaryform.setRestTime(60);

			model.addAttribute("startHour", summaryform.getStartHour());
			model.addAttribute("startMinute", formattedStartMinute);
			model.addAttribute("finishHour", summaryform.getFinishHour());
			model.addAttribute("finishMinute", formattedFinishMinute);
			model.addAttribute("restTime", summaryform.getRestTime());

		}

		// 休日、欠勤、振休のとき、労働時間を自動で0にする
		if ("休日".equals(summaryform.getAttendanceType()) || "欠勤".equals(summaryform.getAttendanceType())
				|| "振休".equals(summaryform.getAttendanceType())) {

			summaryform.setStartHour(0);
			summaryform.setStartMinute(0);
			summaryform.setFinishHour(0);
			summaryform.setFinishMinute(0);
			summaryform.setRestTime(0);

			model.addAttribute("startHour", summaryform.getStartHour());
			model.addAttribute("startMinute", formattedStartMinute);
			model.addAttribute("finishHour", summaryform.getFinishHour());
			model.addAttribute("finishMinute", formattedFinishMinute);
			model.addAttribute("restTime", summaryform.getRestTime());

		}

		// 出勤・振出の退勤時刻が22:45を過ぎているときにエラー表示
		if (("出勤".equals(summaryform.getAttendanceType()) || "振出".equals(summaryform.getAttendanceType()))
				&& (summaryform.getFinishHour() == 22) && (summaryform.getFinishMinute() > 45)) {

			model.addAttribute("message", "退勤時刻は22:45以降にできません");

			return "edit";
		}

		// 休憩時間が4桁以上の時にエラー表示
		if (summaryform.getRestTime() > 999) {

			model.addAttribute("message", "休憩時間は４桁以上にできません");

			return "edit";

		}
		
		// 退勤時間が出勤時間より前に申請されるときにエラー表示
		if (summaryform.getStartHour() > summaryform.getFinishHour()) {
			
			model.addAttribute("message", "退勤時刻を出勤時刻より前の時間にできません");
			
			return "edit";
		}
		if ((summaryform.getStartHour() == summaryform.getFinishHour()) && (summaryform.getStartMinute() > summaryform.getFinishMinute())) {

			model.addAttribute("message", "退勤時刻を出勤時刻より前の時間にできません");

			return "edit";
		}
		
		
		
		// 休憩時間が労働時間より多くなってしまう場合にエラー表示
		int workTime = (summaryform.getFinishHour() * 60 + summaryform.getFinishMinute()) - (summaryform.getStartHour() * 60 + summaryform.getStartMinute());
		
		if (workTime < summaryform.getRestTime()) {
			
			model.addAttribute("message", "休憩時間が労働時間より多くなっています");
			
			return "edit";
			
		}
		
		
		// 実労働時間が４時間以上のとき、
		int realWorkTime = workTime - summaryform.getRestTime();
		
		if (realWorkTime >= 240 && summaryform.getRestTime() < 60) {
			
			model.addAttribute("message", "実労働時間が4時間以上の為、休憩時間が60分以上必要です");
			
			return "edit";
		}
		
		
		
		

		// 年休・振休が休日になるときにエラーを返す

		Boolean compose = hservice.holidayBridge(summaryform.getDate());

		int countPaidHoliday = hservice.paidHolidayBridge(summaryform.getEmployeeNum());

		// 会社休日の日に申請できない区分のエラー文表示
		if (("出勤".equals(summaryform.getAttendanceType()) || "年休".equals(summaryform.getAttendanceType())
				|| "振休".equals(summaryform.getAttendanceType()) || "欠勤".equals(summaryform.getAttendanceType()))
				&& compose) {

			model.addAttribute("message", "出勤・年休・振休・欠勤は会社休日の日に申請できません");

			return "edit";
		}

		// 有給申請で有給残日数が足りないときのエラー表示
		if ("年休".equals(summaryform.getAttendanceType()) && countPaidHoliday < 1) {

			model.addAttribute("message", "有給残日数が足りません");

			return "edit";
		}

		// 休日、振出の取得日付が平日になるときにエラーを返す

		if (("休日".equals(summaryform.getAttendanceType()) || "振出".equals(summaryform.getAttendanceType())) && !compose) {

			
			model.addAttribute("message", "休日と振出は平日に申請できません");

			return "edit";
		}

		// 振休残日数が0のとき、振出申請でエラー表示
		int countSubstitudeHoliday = sservice.substitudeBridge(summaryform.getEmployeeNum());

		System.out.println("countSubstitudeHoliday：" + countSubstitudeHoliday);

		if ("振休".equals(summaryform.getAttendanceType()) && countSubstitudeHoliday < 1) {

			model.addAttribute("message", "振休残日数が足りません");

			return "edit";
		}

		
		return "edit-confirm";
	}
	
}
