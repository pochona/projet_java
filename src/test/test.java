package test;

import java.io.IOException;

import application.Avion;

public class test {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Avion.initialise();
			System.out.println(Avion.afficherLesAvions());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
