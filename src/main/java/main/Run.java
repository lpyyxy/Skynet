package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Run {
	public static void main() {
		Logger logger = LoggerFactory.getLogger(new Start().getClass());
		logger.info("Æô¶¯³É¹¦");
		MainData.setServerStatusEnum(ServerStatusEnum.RUNING);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        do {
            try {
				str = br.readLine();
			} catch (IOException e) {
				logger.info(e.toString());
			}
        } while (!str.equals("end"));
		Close.main();
	}
}
