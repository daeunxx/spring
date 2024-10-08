package core.springbasic.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;

  @Autowired
  public MemberServiceImpl(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Override
  public void join(Member member) {
    memberRepository.save(member);
  }

  @Override
  public Member findMember(Long id) {
    return memberRepository.findById(id);
  }

  // 테스트 용도
  public MemberRepository getMemberRepository() {
    return memberRepository;
  }
}
