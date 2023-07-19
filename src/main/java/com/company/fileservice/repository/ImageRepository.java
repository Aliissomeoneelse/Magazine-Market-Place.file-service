package com.company.fileservice.repository;

import com.company.fileservice.modul.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    Optional<Image> findByImageIdAndDeleteAtIsNull(Integer imageId);

    boolean existsByToken(String token);
}
