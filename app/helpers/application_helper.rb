module ApplicationHelper
  def show_avatar user, avatar_size
    link_to image_tag(user.avatar,
      alt: user.name, size: avatar_size, class: "avatar"), "#"
  end

  def show_cover book
    link_to image_tag(book.cover, alt: book.title, size: Settings.size_of_book_cover,
      class: "book-cover"), book
  end

  def show_book_introduce book
    truncate strip_tags(book.introduce), length: Settings.length_sub_introduce, separator: " "
  end

  def show_book_title book
    truncate book.title, length: Settings.length_sub_title, separator: " "
  end
end
