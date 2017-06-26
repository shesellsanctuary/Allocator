/**
 * 
 */
package allocator.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import allocator.data.Database;
import allocator.domain.Room;
import allocator.domain.Session;

/**
 * @author felipebertoldo, emilycalvet, marcellotomazi
 * @version %I%, %G%
 */

public class Schedule {
	
	private float fitnessValue;
	private List<Integer> meetCriteria;
	private List<Integer> scheduleAllocation;
	private HashMap<String,Integer> sessionMap;
	private int roomCount;
	private int sessionCount;
	private List<Session> sessionList;
	private List<Room> roomList;
	
	/**
	 * Class constructor
	 */
	public Schedule(Database db) {
	
		meetCriteria = new ArrayList<Integer>();
		scheduleAllocation = new ArrayList<Integer>();
		sessionMap = new HashMap<String,Integer>();
		roomCount = db.getRoomList().size();
		sessionCount = db.getSessionList().size();
		sessionList = db.getSessionList();
		roomList = db.getRoomList();
	}
	
	/**
	 * TODO
	 */
	public void createSolution() {
			
		String startTime, sessionId;
		Session currentSession;
		int duration;
		int weekday;
		int doneClassAllocation;
		
		for (int session = 0; session < sessionCount; session++) {
			
			currentSession = sessionList.get(session);
			startTime = currentSession.getStartTime();
			duration = currentSession.getDuration();
			weekday = currentSession.getWeekday();
			sessionId = currentSession.getId();
			
			doneClassAllocation = allocateClass(currentSession, startTime, duration, weekday, sessionId);
			
			if (doneClassAllocation != 1) {
				System.out.println("Oops! Sounds like there is not enough rooms for your classes!");
				// TODO: Adicionar Exception e parar código
			}
		}	
	}
	
	/**
	 * TODO
	 */
	public void calculateFitness() {
		//TODO
	}
	
	/**
	 * TODO
	 * @return
	 */
	public float getFitness() {
		
		return this.fitnessValue;
	}
	
	private int allocateClass(Session session, String startTime, int duration, int weekday, String sessionId) {
		
		int allocated = 0, slotTry = -1, randomInt = -1, possibleSlotsScheduleSize = -1, durationInSlots = 0;
		int startTimeSymbol;
		Random randomGenerator = new Random();
		List<Integer> possibleSlotsSchedule = new ArrayList<Integer>();

		durationInSlots = duration/120;
			
		startTimeSymbol = discoverStartTime(startTime);
		if (startTimeSymbol == -1) {
			
			System.out.println("Oops! Sounds like we have a class that starts in a very strange hour!");
			// TODO: Adicionar Exception e parar código
		}
		
		possibleSlotsSchedule = createListOfPossibleSlots(startTimeSymbol, weekday);
		
		possibleSlotsScheduleSize = possibleSlotsSchedule.size();
		
		
		
		while (possibleSlotsScheduleSize > 0) {
			
			randomInt = randomGenerator.nextInt(possibleSlotsScheduleSize - 1);			
			slotTry = possibleSlotsSchedule.get(randomInt);
			
			if ((scheduleAllocation.get(slotTry) == 0)) {
				
				allocated = 1;
				for (int currentSlot = 1; currentSlot < durationInSlots; currentSlot ++) {
					if ((scheduleAllocation.get(slotTry+currentSlot) != 0)) {
						allocated = 0;
					}				
				}
				if (allocated == 1) {
					
					for (int currentSlot = 0; currentSlot < durationInSlots; currentSlot ++) {
						
						scheduleAllocation.set(slotTry+currentSlot, 1);
					}
					sessionMap.put(sessionId, slotTry);
					return allocated;
				}
				else {
					
					possibleSlotsSchedule.remove(slotTry);
				}
			
			}
			else {
				
				possibleSlotsSchedule.remove(slotTry);
			}
		}

		return allocated;
	}
	
	private int discoverStartTime(String startTime) {
		
		if (startTime == "08:30") return 0;
		else if (startTime == "10:30") return 1;
		else if (startTime == "13:30") return 2;
		else if (startTime == "15:30") return 3;
		else return -1;
	}
	
	private List<Integer> createListOfPossibleSlots(int startTime, int weekday) {
		
		int firstSlot =  roomCount*4*(weekday-1) + startTime;
		int lastSlot = firstSlot + roomCount*4;
		List<Integer> possibleSlotsSchedule = new ArrayList<Integer>();
		
		for (int slot = firstSlot; slot < lastSlot; slot =+ 4) {
			
			if (scheduleAllocation.get(slot) != 0) {
				
				possibleSlotsSchedule.add(slot);
			}
		}
		
		return possibleSlotsSchedule;
	}

}
