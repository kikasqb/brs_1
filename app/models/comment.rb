class Comment < ApplicationRecord
  belongs_to :commentator

  validates :content, presence: true,
    length: {maximum: Settings.max_lenght_content_comment}

  default_scope {where deleted: false}

  delegate :name, to: :commentator, allow_nil: true
  delegate :user, to: :commentator, allow_nil: true
  delegate :title, to: :commentator, allow_nil: true
  delegate :review, to: :commentator, allow_nil: true
end
