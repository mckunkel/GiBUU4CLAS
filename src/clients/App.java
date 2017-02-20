package clients;

import domain.ReadGiBuuOutput;

public class App {

	public static void main(String[] args) {

		String inputDir = "/Volumes/Mac_Storage/Work_Codes/GiBUU/clas/diLepton/fileOutput/";
		String filePart = "Dilepton_FullEvents_";
		String outputDir = "/Volumes/Mac_Storage/Work_Codes/CLAS12/diLepton/diLeptonLund/";
		for (int i = 0; i < 10; i++) {
			ReadGiBuuOutput myreader = new ReadGiBuuOutput(inputDir + filePart + i + ".dat",
					outputDir + filePart + i + ".lund");

			myreader.runConversion();
			System.out.println("Done with " + outputDir + filePart + i + ".lund");
		}
		System.out.println("Done!");
		System.exit(0);
	}

}
