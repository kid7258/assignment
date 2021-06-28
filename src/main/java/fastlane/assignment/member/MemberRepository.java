package fastlane.assignment.member;

public interface MemberRepository {
    void save(Member member);
    void deleteById(String id);
    Member findById(String id);
    void changePassword(String id, String password);
}
