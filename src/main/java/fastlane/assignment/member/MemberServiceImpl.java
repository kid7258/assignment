package fastlane.assignment.member;

import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public void changePassword(String id, String password, String newPassword) {
        memberRepository.changePassword(id, newPassword);
    }

    @Override
    public void deleteMember(String id) {
        memberRepository.deleteById(id);
    }

    @Override
    public Member findMember(String id) {
        return memberRepository.findById(id);
    }
}
