Rails.application.routes.draw do
  root "static_pages#home"
  mount Ckeditor::Engine => "/ckeditor"
  devise_for :users, controllers: {registrations: "registrations"}
  resources :books, only: [:index, :show]
  namespace :admin do
    resources :books, except: :show
    resources :categories, except: :show
  end
end
