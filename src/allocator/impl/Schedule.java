/**
 * 
 */
package allocator.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import allocator.data.Database;
import allocator.domain.Group;
import allocator.domain.Room;
import allocator.domain.Session;

/**
 * @author felipebertoldo, emilycalvet, marcellotomazi
 * @version %I%, %G%
 */

public class Schedule {
	
	public static final int classesPerDay = 4;
	private float fitnessValue;
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
	
		roomCount = db.getRoomList().size();
		scheduleAllocation = new ArrayList<Integer>();
		initializeScheduleAllocation();
		sessionMap = new HashMap<String,Integer>();
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
		int duration = -1, weekday = -1, doneClassAllocation = -1;
		
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
		
		Session currentSession;
		Room currentRoom;
		Group currentGroup;
		List<Integer> sessionFeaturesList, roomFeaturesList;
		int slot = -1, sessionPoints = 0, sessionStudentsNum = -1, roomSeats = -1, failedFeatures = 0, totalSessionPoints = 0;
		boolean alreadyExists;
		
		for (int session = 0; session < sessionCount; session++) {
			
			sessionPoints = 0;
			failedFeatures = 0;
			
			currentSession = sessionList.get(session);
			slot = sessionMap.get(currentSession.getId());
			
			// First possible point: if a session is in a spare room
			alreadyExists = sessionMap.containsValue(slot);
			
			if (alreadyExists == false) {
				
				sessionPoints++;
			}
			
			currentRoom = getSessionRoom(slot, currentSession.getWeekday());
			
			// Second possible point: if a session is in a room with enough available seats
			currentGroup = currentSession.getGroup();
			sessionStudentsNum = currentGroup.getNumberOfStudents();
			
			roomSeats = currentRoom.getNumberOfPlaces();
			
			if (roomSeats >= sessionStudentsNum) {
				
				sessionPoints++;
			}
			
			// Third possible point: if a session is in a room with all the needed features
			sessionFeaturesList = currentSession.getFeaturesList();			
			roomFeaturesList = currentRoom.getFeatures();	
			
			for (int feature = 0; feature < sessionFeaturesList.size(); feature++) {
				
				if (!roomFeaturesList.contains(sessionFeaturesList.get(feature))) {
					failedFeatures = 1;
				}
			}
			
			if (failedFeatures == 0) {
				
				sessionPoints++;
			}
			
			// Add the session points to the sum of total sessions points
			totalSessionPoints += sessionPoints;
		}
		
		// The fitness value is the sum of the points of all session of the schedule, divided
		// by the total number of points that a schedule can achieve: The number of session times 3
		fitnessValue = totalSessionPoints/(3*sessionCount);

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
	
	public int discoverStartTime(String startTime) {
		
		if (startTime.equals("08:30")) return 0;
		else if (startTime.equals("10:30")) return 1;
		else if (startTime.equals("13:30")) return 2;
		else if (startTime.equals("15:30")) return 3;
		else return -1;
	}
	
	private List<Integer> createListOfPossibleSlots(int startTime, int weekday) {
		
		int firstSlot =  roomCount*classesPerDay*(weekday-1) + startTime;
		int lastSlot = firstSlot + roomCount*classesPerDay;
		List<Integer> possibleSlotsSchedule = new ArrayList<Integer>();
		
		for (int slot = firstSlot; slot < lastSlot; slot =+ classesPerDay) {
			
			if (scheduleAllocation.get(slot) != 0) {
				
				possibleSlotsSchedule.add(slot);
			}
		}
		
		return possibleSlotsSchedule;
	}
	
	public HashMap<String,Integer> getSessionMap() {
		return sessionMap;
	}
	
	public void setSessionMap(HashMap<String,Integer> newSessionMap) {
		sessionMap = newSessionMap;
	}
	
	private Room getSessionRoom(int slot, int weekday) {
		
		int weekdayNumberOfSlots, roomIndex;
		Room currentRoom;
		
		weekdayNumberOfSlots = roomCount*classesPerDay;
		roomIndex = (slot-(weekday*weekdayNumberOfSlots))/classesPerDay;
		
		currentRoom = roomList.get(roomIndex);
		
		return currentRoom;		
		
	}
	
	private void initializeScheduleAllocation() {
		
		for (int i = 0; i < 560; i++) {
			scheduleAllocation.add(0);
		}
	}
	
}


