package us.codecraft.blackhole.answer;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import us.codecraft.blackhole.antipollution.WhiteHostManager;

@Component
public class WhiteAnswerProvider implements AnswerProvider {
    
	@Autowired
	private WhiteHostManager whiteHostManager;
	
	
	@Override
	public String getAnswer(String query, int type) {
		
		if(whiteHostManager.whitelist.isEmpty())
			return null;
		Iterator<String> iter = whiteHostManager.whitelist.iterator();
		while (iter.hasNext()) {
		  String domain =	iter.next()+".";
			System.out.println(domain+":"+query+":"+domain.equals(query));

		  if(domain.equals(query)){
			  return null;
		  }
			
		}
			
		
		return "127.0.0.1";
	}

}
