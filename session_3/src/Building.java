public class Building {
    private String buildingName;
    private Room room;

    public Building(String buildingName, String roomName) {
        this.buildingName = buildingName;
        this.room = new Room(roomName);
    }

    public void displayBuilding() {
        System.out.println("Building: " + buildingName);
        room.displayRoom();
    }

}
