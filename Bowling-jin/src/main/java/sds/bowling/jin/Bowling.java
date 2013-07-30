package sds.bowling.jin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Bowling {

	BowFrame frame = null;

	int nowFrame = 1;
	int nowScore = 1;
	boolean strikeYN = false;
	boolean startFlag = false;
	InputStreamReader sr;
	BufferedReader br;
	boolean flag = true;
	int ran = 0;
	int count = 0;
	int index = 0;

	public int roll(ArrayList<BowFrame> arr) {
		sr = new InputStreamReader(System.in);
		br = new BufferedReader(sr);
		int score = 0;
		String input = "";

		index = frame.getRound() - 1;
		while (flag) {
			ran = 11;
			System.out.println(nowFrame + " 프레임 " + nowScore + " 번째 공 ");
			System.out.println();
			
			/* 값을 입력 할 경우
			System.out.println("값을 입력 하시오");
			  try {
				input = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			score = Integer.parseInt(input);
			  */
			
			
			C_secondran();

			C_randomnumber();
			//랜덤으로 값을 추출할 경우
			score = (int) (Math.random() * ran);
			System.out.println("점수가  : " + score + "점 입니다.");
			System.out.println();

			if (nowFrame == 10) {

				scoreCheckTenFrame(arr, score);
				setArrayList(arr, frame);
				frameScore();
				nowScore2(arr, score);
				if (nowScore < 4)
					strike(arr);
				else {
					tenTotalScore(arr, score);
				}

			} else {
				scoreCheck(arr, score);
				setArrayList(arr, frame);
				frameScore();
				nowScore2(arr, score);
				strike(arr);
				rollCnt2_isStrike(arr, score);
			}

			 finalScore(arr);
			count++;
		}
		return 0;
	}

	private void rollCnt2_isStrike(ArrayList<BowFrame> arr, int score) {
		if (frame.isStrike() || frame.rollCnt == 2) {
			totalScore(arr, score);
		}
	}

	private void nowScore2(ArrayList<BowFrame> arr, int score) {
		if (nowScore == 2) {
			spare(arr, score);
		}
	}

	private void C_secondran() {
		if (nowScore == 2 && !strikeYN) {

			ran = 11 - frame.getFirstball();

		}
	}

	private void C_randomnumber() {
		if (nowFrame == 10) {

			nowScore2_strikeYN();
			C_secondran();
			nowScore3_Framescore10();
			if (nowScore == 3 && frame.getFramescore() != 10) {
				ran = 11 - frame.getSecondball();
			}

		}
	}

	private void nowScore3_Framescore10() {
		if (nowScore == 3 && frame.getFramescore() == 10) {
			ran = 11;
		}
	}

	private void nowScore2_strikeYN() {
		if (nowScore == 2 && strikeYN) {
			ran = 11;
		}
	}

	public void scoreCheck(ArrayList<BowFrame> arr, int value) {

		if (nowScore == 1) {
			nowScore++;

			// 점수넣기
			scoreCheck_startFlag(value);
			scoreCheck_value10(value);
			return;
		}
		if (nowScore == 2) {
			if (strikeYN) {
				nowFrame++;
				setNewFrame();
				frame.setRound(nowFrame);
				frame.setFirstball(value);
				nowScore = 1;
				frame.rollCnt++;
				strikeYN = false;
				return;
			}
			frame.setSecondball(value);
			frame.rollCnt++;
			nowScore = 1;
			nowFrame++;
			return;
		}
	}

	public void scoreCheck_startFlag(int value) {
		if (startFlag) {
			frame.rollCnt++;
			startFlag = false;
			frame.setRound(nowFrame);
			frame.setFirstball(value);
			// strike(arr);

		} else {
			setNewFrame();
			frame.setRound(nowFrame);
			frame.setFirstball(value);
			frame.rollCnt++;
			strikeYN = false;
		}
	}

	public void scoreCheck_value10(int value) {
		if (value == 10) {
			nowScore = 2;
			strikeYN = true;
			frame.setStrike(true);
		}
	}

	public void frameScore() {
		int first = frame.getFirstball();
		int second = frame.getSecondball();
		int fin = frame.getFinalball();
		int sum = first + second;

		frame.setFramescore(sum);
		frameScore_Spareyn();
		frameScore_nowFrame10(fin, sum);
	}

	public void frameScore_Spareyn() {
		if (frame.getFramescore() == 10) {
			frame.setSpareyn(true);
		}
	}

	public void frameScore_nowFrame10(int fin, int sum) {
		if (nowFrame == 10) {
			frameScore_nowFrame10_check(fin, sum);

		}
	}

	public void frameScore_nowFrame10_check(int fin, int sum) {
		if (!(nowScore == 1 && strikeYN || nowScore == 2 && frame.isSpareyn() || nowScore == 2
				&& frame.isStrike())) {
			frame.setFramescore(sum + fin);
			frame.setSpareyn(true);

		}
	}

	public void strike(ArrayList<BowFrame> arr) {

		int strikeCnt = 2;
		BowFrame record = null;
		int i = frame.getRound() - 1;
		if (i == 0) {
			return;
		}
		if (i == 1) {
			record = arr.get(0);
			strike_isStrike(arr, record);
			record = strike_index2(arr, record, i);
			return;
		}

		while (strikeCnt > -1) {
			record = arr.get(i);
			i--;
			strikeCnt = strikeCnt - record.rollCnt;
		}
		strike_isStrike_else_total(arr, record, i);
	}

	public void strike_isStrike_else_total(ArrayList<BowFrame> arr,
			BowFrame record, int i) {
		if (record.isStrike()) {
			BowFrame totalRecord = arr.get(i);
			int totalScore = totalRecord.getTotalscore()
					+ frame.getFramescore() + record.getFramescore();
			record.setTotalscore(totalScore);
			arr.set(i + 1, record);

		}
	}

	public BowFrame strike_index2(ArrayList<BowFrame> arr, BowFrame record,
			int i) {
		if (i == 2) {
			record = arr.get(0);
			strike_isStrike_total(arr, record);
		}
		return record;
	}

	public void strike_isStrike_total(ArrayList<BowFrame> arr, BowFrame record) {
		if (record.isStrike()) {
			int totalScore = record.getTotalscore() + frame.getFramescore();
			record.setTotalscore(totalScore);
			arr.set(0, record);
		}
	}

	public void strike_isStrike(ArrayList<BowFrame> arr, BowFrame record) {
		if (record.isStrike()) {
			int totalScore = record.getFirstball() + frame.getFramescore();
			record.setTotalscore(totalScore);
			arr.set(0, record);
		}
	}

	public void spare(ArrayList<BowFrame> arr, int value) {
		int i = frame.getRound() - 1;
		int totalScore = 0;
		BowFrame record = null;
		if (i == 0) {
			return;
		}
		if (i == 1) {
			record = arr.get(0);
			if (record.isSpareyn()) {
				totalScore = record.getFramescore() + value;
				record.setTotalscore(totalScore);
				arr.set(0, record);
			}
			return;
		}

		record = arr.get(i - 1);
		spare_isSpareyn(arr, value, i, record);
	}

	public void spare_isSpareyn(ArrayList<BowFrame> arr, int value, int i,
			BowFrame record) {
		spare_isSpareyn_check(arr, value, i, record);
	}

	public void spare_isSpareyn_check(ArrayList<BowFrame> arr, int value,
			int i, BowFrame record) {
		int totalScore;
		if (record.isSpareyn()) {
			BowFrame totalDate = arr.get(i - 2);
			totalScore = totalDate.getTotalscore() + value
					+ record.getFramescore();
			record.setTotalscore(totalScore);
			arr.set(i - 1, record);

		}
	}

	public void totalScore(ArrayList<BowFrame> arr, int value) {

		int i = frame.getRound() - 1;
		int totalScore = 0;
		BowFrame record = null;
		if (i == 0) {
			totalScore = frame.getFramescore();
			frame.setTotalscore(totalScore);
			arr.set(i, frame);
			return;
		}

		totalScore_frame_isStrike(arr, i);
	}

	public void totalScore_frame_isStrike(ArrayList<BowFrame> arr, int i) {
		int totalScore;
		BowFrame record;
		if (!frame.isStrike()) {
			record = arr.get(i - 1);
			totalScore = record.getTotalscore() + frame.getFramescore();
			frame.setTotalscore(totalScore);
			arr.set(i, frame);

		}
	}

	public void finalScore(ArrayList<BowFrame> arr) {

		finalScore_round(arr);
		finalScore_score(arr);
		finalScore_total(arr);
	}

	public void finalScore_total(ArrayList<BowFrame> arr) {
		System.out.println();
		System.out
				.println("---------------------------------------------------------------------------------");
		for (int i = 0; i < arr.size(); i++) {
			BowFrame result = arr.get(i);
			if (i == 9) {
				System.out.printf("|  %3d\t|", result.getTotalscore());
			} else {
				System.out.printf("|  %3d\t", result.getTotalscore());
			}
		}
		System.out.println();
		System.out
				.println("---------------------------------------------------------------------------------");
	}

	public void finalScore_score(ArrayList<BowFrame> arr) {
		System.out.println();
		System.out
				.println("---------------------------------------------------------------------------------");
		for (int i = 0; i < arr.size(); i++) {
			BowFrame result = arr.get(i);
			if (i == 9) {

				System.out.printf("|%1d|%1d|%1d\t|", result.getFirstball(),
						result.getSecondball(), result.getFinalball());

			} else {

				System.out.printf("|  %1d|%1d\t", result.getFirstball(),
						result.getSecondball());
			}
		}
	}

	public void finalScore_round(ArrayList<BowFrame> arr) {
		System.out
				.println("---------------------------------------------------------------------------------");
		for (int i = 0; i < arr.size(); i++) {
			BowFrame result = arr.get(i);
			if (i == 9) {
				System.out.printf("|   %3d\t|", result.getRound());
			} else {
				System.out.printf("|   %3d\t", result.getRound());
			}
		}
	}

	public void setNewFrame() {
		frame = new BowFrame();
	}

	public void setArrayList(ArrayList<BowFrame> arr, BowFrame record) {
		int i = frame.getRound();
		int size = arr.size();
		if (i > size) {
			arr.add(record);
			return;
		}
		arr.set(i - 1, record);
	}

	public void scoreCheckTenFrame(ArrayList<BowFrame> arr, int value) {

		if (nowScore == 1) {
			setNewFrame();
			frame.setRound(nowFrame);
			frame.setFirstball(value);
			if (value == 10) {
				nowScore = 2;
				strikeYN = true;
				frame.setStrike(true);
				return;
			}
		} else if (nowScore == 2) {
			frame.setSecondball(value);
			int sparevalue = frame.getFirstball() + frame.getSecondball();
			if (sparevalue == 10) {
				frame.setSpareyn(true);
			}
		} else if (nowScore == 3) {
			frame.setFinalball(value);
		}

		nowScore++;

		frame.rollCnt++;
	}

	public void tenTotalScore(ArrayList<BowFrame> arr, int value) {

		int i = frame.getRound() - 1;
		int totalScore = 0;
		BowFrame record = null;

		tenTotalScore_framescorenot10();

		if (i == 0) {
			totalScore = frame.getFramescore() + frame.getFinalball();
			frame.setTotalscore(totalScore);
			arr.set(i, frame);
			return;
		}

		tenTotalScore_strike_check(arr, i);

		System.out.println("게임이 종료 되었습니다.");
		flag = false;
		/* total 합계산 */
	}

	public void tenTotalScore_strike_check(ArrayList<BowFrame> arr, int i) {
		int totalScore;
		BowFrame record;
		if (!frame.isStrike()) {
			record = arr.get(i - 1);
			totalScore = record.getTotalscore() + frame.getFramescore()
					+ frame.getFinalball();
			frame.setTotalscore(totalScore);
			arr.set(i, frame);

		}
	}

	public void tenTotalScore_framescorenot10() {
		if (frame.getFramescore() != 10) {
			frame.setFinalball(0);
		}
	}
}
