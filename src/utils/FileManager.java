package utils;

import java.net.URL;

public class FileManager {

	/**Gets the InputStream of the specified filename.
	 *
	 * @param filepath		The path to the file
	 * @return URL			The URL of the file
	 *
	 */
	public static URL findFile(String filepath) {
		
		return FileManager.class.getResource(filepath);

	}

}
