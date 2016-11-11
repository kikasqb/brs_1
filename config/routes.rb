Rails.application.routes.draw do
  root "static_pages#home"
  mount Ckeditor::Engine => '/ckeditor'
  devise_for :users
end
