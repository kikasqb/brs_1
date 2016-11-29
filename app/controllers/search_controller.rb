class SearchController < ApplicationController
  def index
    @key = params[:key]
    @books = Book.search(@key) .page(params[:page_books])
      .per Settings.per_page_search
    @users = User.search(@key).page(params[:page_users])
      .per Settings.per_page_search
  end
end
