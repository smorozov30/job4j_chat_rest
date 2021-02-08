package ru.job4j.chat_rest_api.service.room;

import ru.job4j.chat_rest_api.domian.Room;

import java.util.List;

public interface RoomService {
    List<Room> findAllRooms();

    Room findRoomById(int id);

    Room saveRoom(Room room);

    void deleteRoom(Room room);
}
