class StaticPagesController < ApplicationController
  def home
    @slider_books = Book.order("rate DESC").first 5
    @books = Book.search(params[:key]).of_category(params[:category_id])
      .page(params[:page]).per Settings.book.per_page_book
    @categories = Category.all
    if user_signed_in?
      @reading_books = current_user.books.reading
      @read_books = current_user.books.read
    end
  end
end
