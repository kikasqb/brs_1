class Activity < ApplicationRecord
  belongs_to :user

  validates :target_id, presence: true
  validates :target_type, presence: true
  validates :action_type, presence: true
end
