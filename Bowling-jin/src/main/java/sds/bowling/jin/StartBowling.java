package sds.bowling.jin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StartBowling {
	public static void main(String[] args) throws IOException {
		BowlingGame bow = new BowlingGame();
		bow.setTestCase();
		String[] score = bow.runGame();
		for (String game : score) {
			System.out.println(game);
		}
	}
}

class BowlingGame {
	private static BufferedReader br;
	private int testCase;
	private String[] resultScore;

	public BowlingGame() {
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	private String read() throws IOException {
		return br.readLine().trim();
	}

	public String[] runGame() throws IOException {
		int i = 0;
		while (i < testCase) {
			int n = Integer.parseInt(read());
			String s = br.readLine();
			String[] tmp = s.split("\\s+");
			int tmpLen = tmp.length;
			if (n != tmpLen) {
				System.out
						.println("던진 횟수와 점수의 개수가 일치하지 않습니다. 던진 개수를 다시 입력해 주세요.");
				continue;
			}
			int[] score = new int[tmpLen];
			for (int j = 0; j < tmpLen; j++) {
				score[j] = Integer.parseInt(tmp[j]);
			}
			
			this.resultScore[i] = getGameScore(score);
			i++;
		}
		return this.resultScore;
	}

	private String getGameScore(int[] score) {
		boolean isLast = false;
		int sum = 0;
		int len = score.length;
		for (int i = 0; i < len; i++) {
			sum += score[i];
			if (!isLast && score[i] == 3) // 스트라이크
			{
				sum += nextScoreSum(score, 2, i);
				if (i == len - 3) // 마지막 프레임 첫번재 스트라이크면 끝낸다.
				{
					break;
				}
			} else if (isLast) // 한 프레임의 두번째 샷이라면
			{
				if (score[i] == 3 || score[i - 1] + score[i] == 3) // 스페어
				{
					sum += nextScoreSum(score, 1, i);
				} else if (score[i - 1] + score[i] > 3) // 오류
				{
					return "error";
				}
				if (i == len - 2) // 마지막 첫번째 프레임 두번째 스페어면 끝낸다.
				{
					break;
				}
				isLast = false;
			} else {
				isLast = true;
			}
		}
		return sum + "";
	}

	private int nextScoreSum(int[] score, int idx, int i) {
		int sum = 0;
		for (int j = 1; j <= idx; j++) {
			sum += score[i + j];
		}
		return sum;
	}

	public void setTestCase() throws IOException {
		setTestCase(Integer.parseInt(read()));
	}

	public int getTestCase() {
		return testCase;
	}

	public void setTestCase(int testCase) {
		this.testCase = testCase;
		resultScore = new String[testCase];
	}

	public String[] getResultScore() {
		return resultScore;
	}

	public void setResultScore(String[] resultScore) {
		this.resultScore = resultScore;
	}

}
