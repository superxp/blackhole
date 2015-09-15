package us.codecraft.blackhole.antipollution;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import us.codecraft.blackhole.config.Configure;
import us.codecraft.wifesays.me.ShutDownAble;
import us.codecraft.wifesays.me.StandReadyWorker;


@Component
public class WhiteHostManager extends StandReadyWorker implements InitializingBean,
		ShutDownAble {
	
	private Logger logger = Logger.getLogger(getClass());
    
	public Set<String> whitelist = new HashSet<String>();


	@Override
	public String doWhatYouShouldDo(String whatWifeSays) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void shutDown() {
		// TODO Auto-generated method stub

	}
	
	private void loadFromFile(String filename) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(
				new File(filename)));
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			line = line.trim();
			if (logger.isDebugEnabled()) {
				logger.debug("load whitelist address " + line);
			}
			whitelist.add(line);
		}
		bufferedReader.close();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		String filename = Configure.FILE_PATH + "/whitelist";
		try {
			loadFromFile(filename);
		} catch (IOException e) {
			logger.warn("load file " + filename + " error! " + e);
		}
	}

}
