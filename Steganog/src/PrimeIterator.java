import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class PrimeIterator implements Iterator<Integer>{

	int currentIndex;
	int max;
	List<Integer> nums = new ArrayList<Integer>();
	
	public PrimeIterator(int max){
		currentIndex=0;
		this.max= max;
		setUpList();
	}
	
	public boolean hasNext(){
		Integer hold = currentIndex;
		boolean toReturn = (next()!=0);
		currentIndex = hold;
		return toReturn;
	}
	
	public Integer next(){
		Integer toReturn = 0;
		if(currentIndex<nums.size()){
			toReturn = nums.get(currentIndex);
		}
		currentIndex++;
		return toReturn;
	}
	
	void setUpList(){
		for(int i = 2;i<max;i++){
			nums.add(i);
		}
		int iterator = 0;
		int holdNum;
		while(iterator<nums.size()){
			if(nums.get(iterator)!=0){
				holdNum = nums.get(iterator);
				for(int i = iterator; i<nums.size();i+=holdNum){
					if(nums.get(i)!=0){
						if(nums.get(i)!=holdNum){
							nums.set(i, 0);
						}
					}
				}
			}
			iterator++;
		}
		while(nums.contains(0)){
			nums.remove(nums.indexOf(0));
		}
	}

	//Included due to inheritence. Making java happy.
	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

}
