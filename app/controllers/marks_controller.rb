class MarksController < ApplicationController
  load_and_authorize_resource

  def create
    @messages = Hash.new
    @mark = Mark.find_or_initialize_by book_id: params[:book_id],user_id: current_user.id
    @mark_type = params[:read] == "true" ? true : false
    if @mark.update_attributes read: params[:read]
      @messages[:success] = t :mark_book_success
    else
       @messages[:warning] = t :mark_book_failed
    end
  end
end
