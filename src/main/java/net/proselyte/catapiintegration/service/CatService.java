package net.proselyte.catapiintegration.service;

import feign.FeignException;
import io.github.resilience4j.ratelimiter.RateLimiter;
import lombok.RequiredArgsConstructor;
import net.proselyte.catapiintegration.api.CatsApi;
import net.proselyte.catapiintegration.dto.CatDto;
import net.proselyte.catapiintegration.exceptionhandler.CatApiUnauthorizedException;
import net.proselyte.catapiintegration.mapper.CatMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class CatService {
    private final CatMapper catMapper;
    private final CatsApi catsApi;
    private final RateLimiter catApiRateLimiter;

    public List<CatDto> getAll() {
        Supplier<List<CatDto>> catsSupplier = () -> {
            try {
                var response = catsApi.getCats();
                var externalCats = response.getBody();
                return catMapper.toDto(externalCats);
            } catch (FeignException.Unauthorized e) {
                throw new CatApiUnauthorizedException("Missing of incorrect API key");
            }
        };

        return RateLimiter
                .decorateSupplier(catApiRateLimiter, catsSupplier)
                .get();
    }

    public CatDto getCatById(Long id) {
        Supplier<CatDto> catByIdSupplier = () -> {
            var response = catsApi.getCatById(id);
            var externalCat = response.getBody();
            return catMapper.toDto(externalCat);
        };

        return RateLimiter
                .decorateSupplier(catApiRateLimiter, catByIdSupplier)
                .get();
    }


}
