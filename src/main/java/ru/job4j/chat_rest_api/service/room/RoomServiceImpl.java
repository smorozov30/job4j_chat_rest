package ru.job4j.chat_rest_api.service.room;

import org.springframework.stereotype.Service;
import ru.job4j.chat_rest_api.domian.Room;
import ru.job4j.chat_rest_api.repository.RoomRepository;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository repository;

    public RoomServiceImpl(RoomRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<Room> findAllRooms() {
        return (List<Room>) repository.findAll();
    }

    @Override
    public Room findRoomById(int id) {
        return repository.findById(id).orElse(new Room());
    }

    @Override
    public Room saveRoom(Room room) {
        return repository.save(room);
    }

    @Override
    public void deleteRoom(Room room) {
        repository.delete(room);
    }
}
