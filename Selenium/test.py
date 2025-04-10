# Import
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait, Select
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.keys import Keys

# Initialisation du driver
driver = webdriver.Chrome()
driver.get("http://localhost:4200/login/")

# Timeout
wait = WebDriverWait(driver, timeout=2)

# Test 1
    # Initialisation
login = wait.until(EC.presence_of_element_located((By.XPATH, '/html/body/app-root/main/app-login/form/div[1]/mat-form-field[1]/div[1]/div[2]/div/input')))
pswd = wait.until(EC.presence_of_element_located((By.XPATH, '/html/body/app-root/main/app-login/form/div[1]/mat-form-field[2]/div[1]/div[2]/div/input')))
    
    # Remplissage
login.send_keys("login")
pswd.send_keys("pswd")
    
    # Validation
submit = wait.until(EC.element_to_be_clickable((By.XPATH, '/html/body/app-root/main/app-login/form/div[2]/button')))
submit.submit()
wait.until(EC.url_to_be("http://localhost:4200/accueil"))

# Test 2
btnNewRestaurant = wait.until(EC.element_to_be_clickable((By.XPATH, '/html/body/app-root/main/app-list-restaurants/div/button')))
btnNewRestaurant.click()
wait.until(EC.url_to_be("http://localhost:4200/accueil/addRestaurant"))

nameForm = wait.until(EC.presence_of_element_located((By.XPATH, '/html/body/app-root/main/app-add-restaurant/form/div[1]/mat-form-field[1]/div[1]/div[2]/div/input')))
mat_select = wait.until(EC.element_to_be_clickable((By.ID, "categorie")))
descriptionForm = wait.until(EC.presence_of_element_located((By.XPATH, '/html/body/app-root/main/app-add-restaurant/form/div[1]/mat-form-field[3]/div[1]/div[2]/div/input')))
adressForm = wait.until(EC.presence_of_element_located((By.XPATH, '/html/body/app-root/main/app-add-restaurant/form/div[1]/mat-form-field[4]/div[1]/div[2]/div/input')))
phoneForm = wait.until(EC.presence_of_element_located((By.XPATH, '/html/body/app-root/main/app-add-restaurant/form/div[1]/mat-form-field[5]/div[1]/div[2]/div/input')))
nameForm.send_keys("Restaurant Italien")
mat_select.click()
option_to_select = wait.until(EC.presence_of_element_located((By.XPATH, '//mat-option[.//text()="Italien"]')))
option_to_select.click()
descriptionForm.send_keys("Un restaurant italien")
adressForm.send_keys("1 rue de la Paix, Paris")
phoneForm.send_keys("0123456789")

btnAjouter = wait.until(EC.element_to_be_clickable((By.XPATH, '/html/body/app-root/main/app-add-restaurant/form/div[2]/button')))
btnAjouter.click()
wait.until(EC.url_to_be("http://localhost:4200/accueil"))


# Fermer le navigateur
driver.close()
driver.quit()