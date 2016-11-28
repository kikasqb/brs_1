class Comment < ApplicationRecord
  belongs_to :commentator

  validates :commentator, presence: true
  validates :content, presence: true,
    length: {maximum: Settings.max_lenght_content_comment}

  default_scope {where deleted: false}

  delegate :name, to: :commentator, allow_nil: true
  delegate :user, to: :commentator, allow_nil: true
  delegate :title, to: :commentator, allow_nil: true
  delegate :review, to: :commentator, allow_nil: true
  delegate :review_id, to: :commentator, allow_nil: true

  after_create :call_back_create

  private
  def call_back_create
    create_activity
    mail_notifica_other_commentator
  end
  def mail_notifica_other_commentator
    MailerWorker.perform_async MailerWorker::MAIL_NOTIFICATION, id, Comment.name
  end
  def create_activity
    Activity.create user_id: user.id, target_id: id, target_type: Comment.name,
      action_type: Activity.activity_types[:created]
  end
end
