Rails.application.routes.draw do
  root "static_pages#home"
  mount Ckeditor::Engine => "/ckeditor"
  devise_for :users, controllers: {registrations: "registrations"}
  resources :books, only: [:index, :show]
  namespace :admin do
    resources :books, except: :show
    resources :categories, except: :show
    resources :users, only: [:index, :destroy]
    resources :requests, except: [:new, :create]
  end
  require "sidekiq/web"
  mount Sidekiq::Web, at: "/sidekiq"
  resources :marks
  resources :favorites, only: [:index, :create, :destroy]
  resources :reviews, except: [:new, :index]
  resources :commentators
end
