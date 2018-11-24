package be.kairos.mapper;

public interface Mapper<Domain, Representation> {
    Domain mapToDomain(final Representation representation);

    Representation mapToRepresenation(final Domain domain);
}