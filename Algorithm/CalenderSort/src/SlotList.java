import java.util.PriorityQueue;

public class SlotList{

	private PriorityQueue<Slot> list;

	public SlotList(){
		this.list = new PriorityQueue<Slot>();
	}

	public PriorityQueue<Slot> getList() {
		return list;
	}

	public void setList(PriorityQueue<Slot> list) {
		this.list = list;
	}

	public void addFreeTime(Slot slot) throws CalendarError {
		if (slot == null)
			throw new CalendarError("Null Free Time");

		this.list.add(slot);
	}

}

