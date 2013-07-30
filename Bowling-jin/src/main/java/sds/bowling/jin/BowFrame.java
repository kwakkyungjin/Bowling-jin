package sds.bowling.jin;

public class BowFrame {
	private int round;
	private int firstball;
	private int secondball;
	private int finalball;
	private boolean strike;
	private boolean spareyn;
	private int totalscore;
	private int nowscore;
	private int framescore;
	
	boolean[] tenSt = new boolean[3];
	
	int rollCnt;

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public int getFirstball() {
		return firstball;
	}

	public void setFirstball(int firstball) {
		this.firstball = firstball;
	}

	public int getSecondball() {
		return secondball;
	}

	public void setSecondball(int secondball) {
		this.secondball = secondball;
	}

	public int getFinalball() {
		return finalball;
	}

	public void setFinalball(int finalball) {
		this.finalball = finalball;
	}

	public boolean isStrike() {
		return strike;
	}

	public void setStrike(boolean strike) {
		this.strike = strike;
	}

	public boolean isSpareyn() {
		return spareyn;
	}

	public void setSpareyn(boolean spareyn) {
		this.spareyn = spareyn;
	}

	public int getTotalscore() {
		return totalscore;
	}

	public void setTotalscore(int totalscore) {
		this.totalscore = totalscore;
	}

	public int getNowscore() {
		return nowscore;
	}

	public void setNowscore(int nowscore) {
		this.nowscore = nowscore;
	}

	public int getFramescore() {
		return framescore;
	}

	public void setFramescore(int framescore) {
		this.framescore = framescore;
	}

	public int getRollCnt() {
		return rollCnt;
	}

	public void setRollCnt(int rollCnt) {
		this.rollCnt = rollCnt;
	}

	public boolean[] getTenSt() {
		return tenSt;
	}

	public void setTenSt(boolean[] tenSt) {
		this.tenSt = tenSt;
	}
}
