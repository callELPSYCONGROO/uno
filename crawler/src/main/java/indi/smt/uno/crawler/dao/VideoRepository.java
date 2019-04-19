package indi.smt.uno.crawler.dao;

import indi.smt.uno.crawler.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 無痕剑
 * @date 2019/4/14 22:41
 */
@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

}
