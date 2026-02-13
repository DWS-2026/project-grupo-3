package es.codeurjc.board.service;

import es.codeurjc.board.model.Plant;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

import es.codeurjc.board.model.Plant;

@Service
public class PlantService {

        private ConcurrentMap<Long, Plant> plants = new ConcurrentHashMap<>();
        private AtomicLong nextId = new AtomicLong();

        public Collection<Plant> findAll() {
            return plants.values();
        }

        public Plant findById(long id) {
            return plants.get(id);
        }

        public void save(Plant plant) {

            long id = nextId.getAndIncrement();

            plant.setId(id);

            this.plants.put(id, plant);
        }

        public void deleteById(long id) {
            this.plants.remove(id);
        }


}
