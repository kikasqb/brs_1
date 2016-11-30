class Ability
  include CanCan::Ability

  def initialize(user)
    user ||= User.new
    if user.admin?
      can :manage, :all
    else
      can :read, :all
      can :manage, [Request, Comment, Commentator, Review, Favorite, Like, Mark], user_id: user.id
      can :manage, Relationship, follower_id: user.id
    end
  end
end
