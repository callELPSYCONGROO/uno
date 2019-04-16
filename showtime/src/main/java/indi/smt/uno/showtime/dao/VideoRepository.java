package indi.smt.uno.showtime.dao;

import indi.smt.uno.showtime.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 無痕剑
 * @date 2019/4/14 22:41
 */
@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

	List<Video> findDistinctByCategory(String category);
}
