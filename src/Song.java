import java.util.Arrays;

public class Song {
	
	private String title;
	private String artist;
	private int[] time;
	private static final String INFO_DELIMITER = "; ";
	private static final String TIME_DELIMITER = ":";
	private static final int IDX_TITLE = 0;
	private static final int IDX_ARTIST = 1;
	private static final int IDX_TIME = 2;
	
	public Song(String title, String artist, int[] time) {
		this.title = title;
		this.artist = artist;
		this.time = Arrays.copyOf(time, time.length);
	}
	
	public Song(String info) {
		String[] arr = info.split(INFO_DELIMITER);
		title = arr[0];
		artist = arr[1];
		
		String[] temp = arr[2].split(TIME_DELIMITER);
		
		int[] numArr = new int[temp.length];
		
		for (int i = 0; i < temp.length; ++i) {
			numArr[temp.length-i-1] = Integer.parseInt(temp[i]);
		}
		
		time = numArr;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public int[] getTime() {
		return Arrays.copyOf(time, time.length);
	}
	
	@Override
	public String toString() {
		String a;
		
		if (time.length == 3) {
			a = String.valueOf(time[2]) + TIME_DELIMITER + String.format("%02d", time[1]) + TIME_DELIMITER + String.format("%02d", time[0]);
		}
		else if (time.length == 2) {
			a = String.valueOf(time[1]) + TIME_DELIMITER + String.format("%02d", time[0]);
		}
		else {
			a = String.format("%02d", time[0]);
		}
		String output = title + INFO_DELIMITER + artist + INFO_DELIMITER + a;
		
		return output;
	}
}
