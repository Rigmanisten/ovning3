package se.su.ovning3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Exercise3 {

	private final List<Recording> recordings = new ArrayList<>();

	public void exportRecordings(String fileName) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			for(Recording rec : recordings){
				writer.write("<recording>");
  				writer.newLine();	

				writer.write("<artist>" + rec.getArtist() + "</artist>");
				writer.newLine();

  				writer.write("<title>" + rec.getTitle() + "</title>");
				writer.newLine();

				writer.write("<year>" + rec.getYear() + "</year>");
				writer.newLine();

				writer.write("<genres>");
				writer.newLine();
				for(String genre : rec.getGenre()){
					writer.write("<genre>" + genre + "</genre>");
					writer.newLine();
				}
				writer.write("</genres>");
				writer.newLine();
				writer.write("</recording>");
				writer.newLine();
			}
		} catch (FileNotFoundException e) {
			System.out.printf("%s not found",fileName);
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	public static String tag(String tag, String content){
		return "<" + tag + ">" + content + "</" + tag + ">";
	}

	public void importRecordings(String fileName) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			int antal = Integer.parseInt(reader.readLine());
			String line;
			while ((line = reader.readLine()) != null) {

				String[] parts = line.split(";");

				String title = parts[0];
				String artist = parts[1];
				int year = Integer.parseInt(parts[2]);

				int genreAntal = Integer.parseInt(reader.readLine());
				Set<String> genre = new HashSet<>();
				for(int i = 0; i < genreAntal; i++){
					genre.add(reader.readLine());
				}
				recordings.add(new Recording(title, artist, year, genre));
			}
			reader.close();
			if(recordings.size()!=antal){
				throw new IllegalArgumentException("Fel mängd recordings");
			}
		} catch (FileNotFoundException e) {
			System.out.printf("%s not found",fileName);
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	public Map<Integer, Double> importSales(String fileName) {
		return null;
	}

	public List<Recording> getRecordings() {
		return Collections.unmodifiableList(recordings);
	}

	public void setRecordings(List<Recording> recordings) {
		this.recordings.clear();
		this.recordings.addAll(recordings);
	}
}

