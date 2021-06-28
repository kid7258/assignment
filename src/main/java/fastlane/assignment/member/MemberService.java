package fastlane.assignment.member;

public interface MemberService {
    void join(Member member);
    void changePassword(String id, String password, String newPassword);
    void deleteMember(String id);
    Member findMember(String id);
}
