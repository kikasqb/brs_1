class Commentator < ApplicationRecord
  belongs_to :user
  belongs_to :review
  has_many :comments

  accepts_nested_attributes_for :comments,
    reject_if: proc {|attributes| attributes[:content].blank?}

  default_scope {where deleted: false}

  delegate :name, to: :user, allow_nil: true
  delegate :title, to: :review, allow_nil: true
end
