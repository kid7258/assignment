package fastlane.assignment.member;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @ApiOperation(value = "회원 가입 기능")
    @PostMapping("/members")
    public ResponseEntity join(@RequestBody Member newMember) {
        memberService.join(newMember);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @ApiOperation(value = "회원 삭제 기능")
    @DeleteMapping(value = "/members/{id}")
    public ResponseEntity deleteMember(@PathVariable String id) {
        memberService.deleteMember(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "회원 비밀번호 변경 기능")
    @PatchMapping(value = "/members/{id}")
    public ResponseEntity changePassword(@PathVariable String id, @RequestBody MemberPasswordChangeDto dto) {
        memberService.changePassword(id, dto.getPassword(), dto.getNewPassword());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
