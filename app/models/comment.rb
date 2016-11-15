class Comment < ApplicationRecord
  belongs_to :commentator

  validates :content, presence: true,
    length: {maximum: Settings.max_lenght_content_comment}

  default_scope {where deleted: false}
end
