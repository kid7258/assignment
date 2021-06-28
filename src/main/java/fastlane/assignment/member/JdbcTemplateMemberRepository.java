package fastlane.assignment.member;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcTemplateMemberRepository implements MemberRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Member member) {
        jdbcTemplate.update("insert into member values(?, ?)", member.getId(), member.getPassword());
    }

    @Override
    public Member findById(String id) {
        return jdbcTemplate.queryForObject("select * from member where id = ?", memberRowMapper());
    }

    @Override
    public void deleteById(String id) {
        jdbcTemplate.update("delete from member where id = ?", id);
    }

    @Override
    public void changePassword(String id, String password) {
        jdbcTemplate.update("update member set password = ? where id = ?", password, id);
    }

    private RowMapper<Member> memberRowMapper() {
        return new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member();
                member.setId(rs.getString("id"));
                member.setPassword(rs.getString("password"));
                return member;
            }
        };
    }
}
