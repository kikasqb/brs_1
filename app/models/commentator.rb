class Commentator < ApplicationRecord
  belongs_to :user
  belongs_to :review
  has_many :comments

  default_scope {where deleted: false}
end
