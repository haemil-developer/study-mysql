package study.mysql.domain.member.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import study.mysql.domain.member.entity.MemberNicknameRecord;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberNicknameRecordRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String TABlE = "MemberNicknameRecord";

    static final RowMapper<MemberNicknameRecord> rowMapper = (ResultSet resultSet, int rowNum) -> MemberNicknameRecord
            .builder()
            .id(resultSet.getLong("id"))
            .memberId(resultSet.getLong("memberId"))
            .nickname(resultSet.getString("nickname"))
            .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
            .build();

    public List<MemberNicknameRecord> findAllByMemberId(Long memberId) {
        String sql = String.format("SELECT * FROM %s WHERE memberId = :memberId", TABlE);
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("memberId", memberId);

        return namedParameterJdbcTemplate.query(sql, param, rowMapper);
    }

    public MemberNicknameRecord save(MemberNicknameRecord memberNicknameRecord) {
        if (memberNicknameRecord.getId() == null) {
            return insert(memberNicknameRecord);
        }
        throw new UnsupportedOperationException("MemberNicknameReocrd는 갱신을 지원하지 않습니다.");
    }

    public MemberNicknameRecord insert(MemberNicknameRecord memberNicknameRecord) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName("MemberNicknameRecord")
                .usingGeneratedKeyColumns("id");

        SqlParameterSource params = new BeanPropertySqlParameterSource(memberNicknameRecord);
        long id = simpleJdbcInsert.executeAndReturnKey(params).longValue();
        return MemberNicknameRecord.builder()
                .id(id)
                .memberId(memberNicknameRecord.getMemberId())
                .nickname(memberNicknameRecord.getNickname())
                .createdAt(memberNicknameRecord.getCreatedAt())
                .build();
    }
}
