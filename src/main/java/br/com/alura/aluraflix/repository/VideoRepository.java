package br.com.alura.aluraflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.aluraflix.modelo.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

}
