package peopletraackr;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Worker extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 1L;
	private final String file;

	Worker(String file){
		this.file = file;
	}

	@Override
	protected Integer compute() {
		HashMap<String, RecursiveTask<List<Map.Entry<String, Long>>>> forks = new HashMap<>();
		System.out.println("Reading file...");
		NameList nl = new NameList();
		int index_lines = 0;
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
			Pattern p = Pattern.compile("^(\\w+),\\s+(\\w+)");
			Matcher m;
		    for(String line; (line = br.readLine()) != null; ) {
		    	m = p.matcher(line);
				if (m.find()) {
					index_lines++;
					nl.addLastName(m.group(1));
					nl.addFirstName(m.group(2));
					nl.addFullName(m.group(1)+", "+m.group(2));
				}
		    }
		}catch(Exception e){
			e.printStackTrace();
		}


		System.out.println("File read.");

		System.out.println("Full name list: "+nl.getFullNames().size());
		System.out.println("Last name list: "+nl.getLastNames().size());
		System.out.println("First name list: "+nl.getFirstNames().size());

		System.out.println("Sorting data...");
		BuildNameList bnl_first = new BuildNameList(nl.getFirstNames());
		forks.put("first names", bnl_first);
		bnl_first.fork();

		BuildNameList bnl_last = new BuildNameList(nl.getLastNames());
		forks.put("last names", bnl_last);
		bnl_last.fork();

		for(String key : forks.keySet()){
			RecursiveTask<List<Map.Entry<String, Long>>> task = forks.get(key);
			List<Map.Entry<String, Long>> sort = task.join();
			System.out.println("List: "+key);
			int i = 0;
			for(Map.Entry<String, Long> m : sort){
				if(i>9)
					break;
				System.out.println(m.getKey()+": "+m.getValue());
				i++;
			}
		}


		int n_max = 25; //number of computed names
		System.out.println("Displaying computed "+n_max+" names");
		String full_name;
		int n = 0; // counter for the names
		HashMap<String, Boolean> used_first_names = new HashMap<>();
		for(String lastn : nl.getLastNames().keySet()){
			if(n>= n_max) break;
			for(String firstn : nl.getFirstNames().keySet()){
				full_name = lastn+", "+firstn;
				if(!nl.getFullNames().containsKey(full_name) && !used_first_names.containsKey(firstn)){
					used_first_names.put(firstn, true);
					System.out.println(full_name);
					break;
				}
			}
			n++;
		}

		return index_lines;
	}

}
