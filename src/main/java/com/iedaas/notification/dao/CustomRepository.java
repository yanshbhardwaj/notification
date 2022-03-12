package com.iedaas.notification.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class CustomRepository {

    private static final Logger logger = LoggerFactory.getLogger(CustomRepository.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UUID getUserUUID(String userEmail){
        String sql = String.format("select u.user_uid from user u where u.email_id='%s'", userEmail);
        logger.info("userEmail :={}, sql :={}",userEmail, sql);
        String userUid = jdbcTemplate.queryForObject(sql, String.class);
        assert userUid != null;
        logger.debug("userEmail :={}, sql :={}, userUid :={}",userEmail, sql, userUid);
        return UUID.fromString(userUid);
    }

    public List<UUID> getChecklistRequestFollowersList(UUID checklistRequestUid){
        String sql = String.format("select l.user_uid from like_unlike l where l.checklist_request_uid='%s' and action='follow'", checklistRequestUid);
        logger.info("checklistRequestUid :={}, sql :={}", checklistRequestUid, sql);
        List<UUID> followersUid = jdbcTemplate.queryForList(sql, UUID.class);
        logger.debug("checklistRequestUid :={}, sql :={}, followersList :={}", checklistRequestUid, sql, followersUid);
        return followersUid;
    }

    public List<UUID> getChecklistFollowersList(UUID checklistUid){
        String sql = String.format("select l.user_uid from like_unlike l where l.checklist_request_uid='%s' and action='follow'", checklistUid);
        logger.info("checklistRequestUid :={}, sql :={}", checklistUid, sql);
        List<UUID> followersUid = jdbcTemplate.queryForList(sql, UUID.class);
        logger.debug("checklistUid :={}, sql :={}, followersList :={}", checklistUid, sql, followersUid);
        return followersUid;
    }

    public List<UUID> getUserFollowersList(UUID userUid){
        String sql = String.format("select u.user_uid from user_following u where u.followed_user_uid='%s'", userUid);
        logger.info("userUid :={}, sql :={}", userUid, sql);
        List<UUID> userFollowersList = jdbcTemplate.queryForList(sql, UUID.class);
        logger.debug("userUid :={}, sql :={}, userFollowersList :={}", userUid, sql, userFollowersList);
        return userFollowersList;
    }
}
