package com.example.mreview.repository;

import com.example.mreview.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMembers(){
        IntStream.rangeClosed(1,100).forEach(i->{
            Member member = Member.builder()
                    .email("r"+i+"@zerock.org")
                    .pw("1111")
                    .nickName("reviewer"+i).build();
            memberRepository.save(member);
        });
    }

    @Commit
    @Transactional
    @Test
    public void testDeleteMember(){
        Long mid = 5L; //Member mid
        Member member = Member.builder().mid(mid).build();

//        memberRepository.deleteById(mid);
//        reviewRepository.deleteByMember(member);//ERROR: FK를 가지는 리뷰를 먼저 삭제하지 않았고 트랜잭션 관련 처리가 없음.

        reviewRepository.deleteByMember(member);
        memberRepository.deleteById(mid);
    }
}
