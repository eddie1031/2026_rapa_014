package io.eddie.jwt.dao.adpater;

import io.eddie.jwt.dao.RefreshTokenBlackListRepository;
import io.eddie.jwt.dao.RefreshTokenRepository;
import io.eddie.jwt.dao.TokenRepository;
import io.eddie.jwt.domain.Member;
import io.eddie.jwt.domain.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryAdapter implements TokenRepository {

    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenBlackListRepository blackListRepository;


    @Override
    public RefreshToken save(Member member, String token) {
        return refreshTokenRepository.save(
                RefreshToken.builder()
                        .refreshToken(token)
                        .member(member)
                        .build()
        );
    }

    @Override
    public Optional<RefreshToken> findValidRefTokenByToken(String token) {
        return Optional.empty();
    }

    @Override
    public Optional<RefreshToken> findValidRefTokenByMemberId(Long memberId) {
        return Optional.empty();
    }

    @Override
    public RefreshToken appendBlackList(RefreshToken refreshToken) {
        return null;
    }

}
