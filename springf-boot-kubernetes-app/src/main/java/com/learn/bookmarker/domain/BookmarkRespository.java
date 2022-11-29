package com.learn.bookmarker.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookmarkRespository extends JpaRepository<Bookmark,Long> {

    @Query("select new com.learn.bookmarker.domain.BookmarkDTO(b.id,b.title,b.url,b.createdAt) from Bookmark b")
    Page<BookmarkDTO> findBookmarks(Pageable pageable);

    @Query("""
            select new com.learn.bookmarker.domain.BookmarkDTO(b.id,b.title,b.url,b.createdAt) from Bookmark b
            where lower(b.title) like lower(concat('%',:query,'%')) 
            """)
    Page<BookmarkDTO> searchBookMarks( String query, Pageable pageable);



    //Page<BookmarkDTO> findBookmarkByTitleContainsIgnoreCase( String query, Pageable pageable);

    Page<BookmarkVM> findBookmarkByTitleContainsIgnoreCase( String query, Pageable pageable);
}
