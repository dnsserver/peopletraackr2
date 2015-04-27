package peopletraackr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

@SuppressWarnings("serial")
public class BuildNameList extends RecursiveTask<List<Map.Entry<String, Long>>> {
	HashMap<String, Long> names = new HashMap<>();

	BuildNameList(HashMap<String, Long> names) {
		this.names.putAll(names);
	}

	@Override
	protected List<Map.Entry<String, Long>> compute() {
		List<Map.Entry<String, Long>> list = new ArrayList<>(
			    this.names.entrySet());
			Collections.sort(list, new Comparator<Map.Entry<String, Long>>() {
			  public int compare(Map.Entry<String, Long> e1, Map.Entry<String, Long> e2) {
				  if(e1.getValue() > e2.getValue())
					  return -1;
				  else if (e1.getValue() < e2.getValue())
					  return 1;
				  else
					  return 0;
			  }
			});


		return list;
	}
}
